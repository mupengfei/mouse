package com.mrgan.mouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Mouse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String no;
	@NotNull
	private String groupId;
	private double tumorLength;
	private double tumorWidth;
	@NotNull
	private double tumorVolume;
	private int groupNum;
}
