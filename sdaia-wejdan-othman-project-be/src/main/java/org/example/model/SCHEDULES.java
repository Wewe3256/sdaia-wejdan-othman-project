package org.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SCHEDULES {
    private int id;
    private int doctor_id;
    private String start_time;
    private String end_time;
    private boolean is_available;

    public SCHEDULES() {
    }

    public SCHEDULES(int id, int doctor_id, String start_time, String end_time, boolean is_available) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.is_available = is_available;
    }

    public SCHEDULES (ResultSet rs) throws SQLException {
        id =rs.getInt("id");
        doctor_id =rs.getInt("doctor_id");
        start_time = rs.getString("start_time");
        end_time = rs.getString("end_time");
        is_available =rs.getBoolean("is_available");
    }

    public int getId() {
        return id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getStart_end() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setStart_end(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    @Override
    public String toString() {
        return "SCHEDULES{" +
                "id=" + id +
                ", doctor_id=" + doctor_id +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", is_available=" + is_available +
                '}';
    }
}
