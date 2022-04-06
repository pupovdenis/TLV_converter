package ru.pupov.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

class TLVConverterTest {

    @DisplayName("Проверка цикла конвертации от ститывания бинарного файла до записи json файла")
    @Test
    public void shouldConvertTestBynaryFileToJsonStringAndCreateJsonFile() {
        byte[] bytes = new byte[0];
        String json = null;
        String result = null;
        try {
            Path pathIn = Paths.get(ClassLoader.getSystemResource("data-1.bin").toURI());
            if (!Files.exists(pathIn)) {
                throw new RuntimeException("file " + pathIn.toFile().getName() + " isn't exist");
            }

            bytes = Files.readAllBytes(pathIn);

            Converter converter = new TLVConverter();
            json = converter.getJson(bytes);

            Path tempPathOut = Files.createTempFile("result", ".json");
            System.out.println(tempPathOut.toAbsolutePath());

            Files.write(tempPathOut, json.getBytes(StandardCharsets.UTF_8));
            result = Files.lines(tempPathOut).collect(Collectors.joining());
        } catch (Exception e) {
            fail("Exception", e);
        }

        assertThat(bytes.length).isNotEqualTo(0);
        assertThat(DatatypeConverter.printHexBinary(bytes)).isEqualTo("01000400A83292560200030004710203000B008E8E8E2090AEACA0E8AAA004001D000B00070084EBE0AEAAAEAB0C000200204E0D00020000020E000200409C04001D000B00070084EBE0AEAAAEAB0C000200204E0D00020000020E000200409C");
        assertThat(json).isNotEqualTo(null);
        assertThat(json).isEqualTo(result);
        assertThat(result).isEqualTo("{\"datetime\":\"2016-01-10T15:30:00\",\"orderNumber\":160004,\"customerName\":\"ООО Ромашка\",\"items\":[{\"name\":\"Дырокол\",\"price\":20000,\"quantity\":2.0,\"sum\":40000}]}");
    }
}