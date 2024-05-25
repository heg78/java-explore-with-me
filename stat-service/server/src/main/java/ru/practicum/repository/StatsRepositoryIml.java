package ru.practicum.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.practicum.EndpointHit;
import ru.practicum.ViewStats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatsRepositoryIml implements StatsRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(EndpointHit hit) {
        jdbcTemplate.update("INSERT INTO stats (app, uri, ip, created) VALUES (?, ?, ?, ?)",
                hit.getApp(), hit.getUri(), hit.getIp(), Timestamp.valueOf(hit.getTimestamp()));
    }

    @Override
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        String query = "SELECT app, uri, COUNT ("
                .concat(unique ? "DISTINCT " : "")
                .concat("ip) AS hits FROM stats WHERE (created >= ? AND created <= ?) ")
                .concat(listToSqlIn(uris))
                .concat(" GROUP BY app, uri ORDER BY hits DESC");
        return jdbcTemplate.query(query, this::mapRow, start, end);
    }

    private String listToSqlIn(List<String> uris) {
        if (uris.isEmpty()) return "";
        return ("AND uri IN ('" + String.join("', '", uris) + "') ");
    }

    private ViewStats mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ViewStats.builder()
                .app(rs.getString("app"))
                .uri(rs.getString("uri"))
                .hits(rs.getLong("hits"))
                .build();
    }
}
