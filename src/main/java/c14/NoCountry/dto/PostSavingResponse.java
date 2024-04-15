package c14.NoCountry.dto;


import c14.NoCountry.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSavingResponse {
    private String name;
    private String data;
    private String image;
    private Users user;
}
