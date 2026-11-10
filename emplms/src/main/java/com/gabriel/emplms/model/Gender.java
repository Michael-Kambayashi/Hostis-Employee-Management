package com.gabriel.emplms.model;
import lombok.Data;
import java.util.Date;

@Data
public class Gender{
	int id;
	String name;
	@Override
	public String toString(){
		return name;
	}
}
