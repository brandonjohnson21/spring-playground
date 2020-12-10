package com.example.demo.simplify;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.TimeZone;

@JsonSerialize(using= Activity.ActivitySerializer.class)
public class Activity {
    User user;
    Status status;

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return user.getEmails().stream().filter(Email::isPrimary).map(Email::getAddress).findFirst().orElse("");
    }
    public String getUsername() {
        return user.getUsername();
    }
    public long getUserId() {
        return user.getId();
    }
    public Date getStatusDate() {
        return status.getDate();
    }
    public String getStatusText() {
        return status.getText();
    }

    public static class ActivitySerializer extends StdSerializer<Activity> {

        public ActivitySerializer() {
            this(null);
        }

        public ActivitySerializer(Class<Activity> t) {
            super(t);
        }


        public void serialize(
                Activity value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException, JsonProcessingException {
            boolean isDetailed = Views.DetailView.class.isAssignableFrom(provider.getActiveView());
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            jgen.writeStartObject();
            if (isDetailed) jgen.writeNumberField("userId", value.getUser().getId());
            jgen.writeStringField("user", value.getUser().getUsername());
            if (isDetailed) jgen.writeStringField("email", value.getUser().getEmails().stream().filter(Email::isPrimary).map(Email::getAddress).findFirst().orElse(""));
            jgen.writeObjectField("date",  formatter.format(value.getStatus().getDate()));
            jgen.writeStringField("statusText", value.getStatus().getText());
            jgen.writeEndObject();

        }
    }
}
