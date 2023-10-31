package me.xvepu.platform.okaeri;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.xvepu.platform.notice.NoticeBuilder;
import me.xvepu.platform.notice.NoticeType;

public class NoticeSerializer implements ObjectSerializer<NoticeBuilder> {

    @Override
    public boolean supports(@NonNull Class<? super NoticeBuilder> type) {
        return NoticeBuilder.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull NoticeBuilder object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("type", object.getNoticeType());
        data.add("message", object.getMessage());
    }

    @Override
    public NoticeBuilder deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new NoticeBuilder(
                data.get("type", NoticeType.class),
                data.get("message", String.class)
        );
    }
}