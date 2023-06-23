package com.krishna.blog.payloads;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
//for exposing only the properties whose data is need to be sent
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=4,message="Username must be greater than 4 character")
	private String name;
	
	@SuppressWarnings("deprecation")
	@Email(message="Invaild Email")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="Must be in between 3-10 length")
	private String password;
	
	@NotEmpty
	private String about;
}
