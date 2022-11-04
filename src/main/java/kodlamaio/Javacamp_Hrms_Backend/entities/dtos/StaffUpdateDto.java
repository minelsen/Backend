package kodlamaio.Javacamp_Hrms_Backend.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffUpdateDto {

    private int staffId;

    private String firstName;

    private String lastName;

    private String email;
}
