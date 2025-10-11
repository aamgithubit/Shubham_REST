package g_POJO_with_lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO with Lombok

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class POJOlombok {

	private String name;
	private String email;
	private String gender;
	private String status;

}
