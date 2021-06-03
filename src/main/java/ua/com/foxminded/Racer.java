package ua.com.foxminded;

import java.time.Duration;

public class Racer {

    private String abbreviations;
    private String name;
    private String team;
    private Duration lapTime;

    public Racer(String abbreviations, String name, String team, Duration lapTime) {
        this.abbreviations = abbreviations;
        this.name = name;
        this.team = team;
        this.lapTime = lapTime;
    }

    public String getAbbreviations() {
        return abbreviations;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public Duration getLapTime() {
        return lapTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((abbreviations == null) ? 0 : abbreviations.hashCode());
        result = prime * result + ((lapTime == null) ? 0 : lapTime.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        return result;
    }
    
    

    @Override
    public String toString() {
        return "Racer [abbreviations=" + abbreviations + ", name=" + name + ", team=" + team + ", lapTime=" + lapTime
                + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Racer other = (Racer) obj;
        if (abbreviations == null) {
            if (other.abbreviations != null)
                return false;
        } else if (!abbreviations.equals(other.abbreviations))
            return false;
        if (lapTime == null) {
            if (other.lapTime != null)
                return false;
        } else if (!lapTime.equals(other.lapTime))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (team == null) {
            if (other.team != null)
                return false;
        } else if (!team.equals(other.team))
            return false;
        return true;
    }

}
