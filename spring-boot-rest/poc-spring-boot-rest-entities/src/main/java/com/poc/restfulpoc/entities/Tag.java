/*
 * Copyright 2015-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.poc.restfulpoc.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Tag class.
 *
 * @author Raja Kolli
 * @since 0.2.1
 */
@Entity(name = "Tag")
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor // Only to be compliant with JPA
public class Tag {

	@Id
	@GenericGenerator(name = "sequenceGenerator", strategy = "enhanced-sequence",
			parameters = { @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo"),
					@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
					@org.hibernate.annotations.Parameter(name = "increment_size", value = "5") })
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	public Tag(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Tag other = (Tag) obj;
		return Objects.equals(this.name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}

}
