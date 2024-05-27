package com.techrs.avante_rs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tools_types")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ToolsType extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "toolsTypeList")
    private List<Help> helpList = new ArrayList<>();
}
