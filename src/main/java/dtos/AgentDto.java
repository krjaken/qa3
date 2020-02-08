package dtos;

import lombok.Data;

@Data
public class AgentDto {
    private String id;
    private String name;
    private String workingArea;
    private String commission;
    private String phone;
    private String country;
}
