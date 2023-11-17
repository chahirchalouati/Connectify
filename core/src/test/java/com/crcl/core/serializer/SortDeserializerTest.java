package com.crcl.core.serializer;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SortDeserializerTest {

    @Test
    void deserialize() {
        List<Integer> listOne = IntStream.rangeClosed(1, 10).boxed().toList();
        List<Integer> listTwo = IntStream.rangeClosed(5, 10).boxed().toList();
        List<String> differences = new ArrayList<>(CollectionUtils.subtract(listOne, listTwo));
        assertEquals(3, differences.size());
        assertThat(differences).containsExactly("Tom", "John", "Jack");

    }
}