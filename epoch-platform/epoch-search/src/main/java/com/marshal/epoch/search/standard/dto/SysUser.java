package com.marshal.epoch.search.standard.dto;

import com.marshal.epoch.search.common.annotation.SearchField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@Document(indexName = "epoch_standard", type = "sys_user", shards = 5, replicas = 1)
public class SysUser {

    @Id
    private Long userId;

    @SearchField
    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart_pinyin", searchAnalyzer = "ik_smart_pinyin")
    private String userName;

    @SearchField
    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart_pinyin", searchAnalyzer = "ik_smart_pinyin")
    private String email;

    @SearchField
    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart_pinyin", searchAnalyzer = "ik_smart_pinyin")
    private String phone;

    @SearchField
    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart_pinyin", searchAnalyzer = "ik_smart_pinyin")
    private String description;

    private String avatar;

}
