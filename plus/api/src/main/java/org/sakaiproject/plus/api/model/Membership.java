/*
 * Copyright (c) 2021- Charles R. Severance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.sakaiproject.plus.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sakaiproject.springframework.data.PersistableEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "PLUS_MEMBERSHIP",
  indexes = { @Index(columnList = "SUBJECT_GUID, CONTEXT_GUID") },
  uniqueConstraints = { @UniqueConstraint(columnNames = { "SUBJECT_GUID", "CONTEXT_GUID" }) }
)
@Data
public class Membership extends BaseLTI implements PersistableEntity<Long> {

	public static final Integer ROLE_LEARNER = 0;
	public static final Integer ROLE_INSTRUCTOR = 1000;

	@Id @GeneratedValue
	@Column(name = "MEMBERSHIP_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_GUID", nullable = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Subject subject;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTEXT_GUID", nullable = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Context context;

	@Column(name = "ROLE", nullable = true)
	private Integer role;

	@Column(name = "ROLE_OVERRIDE", nullable = true)
	private Integer roleOverride;
}
