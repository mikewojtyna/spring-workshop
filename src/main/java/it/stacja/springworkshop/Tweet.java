package it.stacja.springworkshop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Tweet {

	private String title;
	@Id
	@GeneratedValue
	private UUID id;
}
