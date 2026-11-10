package com.gabriel.emplms.model;
import lombok.Data;
import java.util.Date;

@Data
public class Status{
	int id;
	String name;
	@Override
	public String toString(){
		return name;
	}
}
