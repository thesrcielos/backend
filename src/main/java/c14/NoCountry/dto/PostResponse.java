package c14.NoCountry.dto;


import c14.NoCountry.Entity.users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Integer id;
    private String name;
    private String data;
    private byte[] image;
    private users user;
}
