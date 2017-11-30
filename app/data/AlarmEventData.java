package data;

import java.util.Date;

public class AlarmEventData {
    private Date datetime;
    private String codeUsed;
    private String eventDescription;

    public AlarmEventData(Date datetime, String codeUsed, String eventDescription) {
        this.datetime = datetime;
        this.codeUsed = codeUsed;
        this.eventDescription = eventDescription;
    }

    public Date getDatetime() {
        return datetime;
    }

    public String getDatetimeAsString() {
        return datetime.toString();
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getCodeUsed() {
        return codeUsed;
    }

    public void setCodeUsed(String codeUsed) {
        this.codeUsed = codeUsed;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
