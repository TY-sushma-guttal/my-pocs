package com.example.deliverymanagement.entity;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address_details")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeliveryDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long deliveryId;

	private Map<String, LocalDateTime> status;

	private String address;

	private String item;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDetails userDetails;

}
