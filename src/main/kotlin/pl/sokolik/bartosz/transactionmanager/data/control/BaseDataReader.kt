package pl.sokolik.bartosz.transactionmanager.data.control

import io.vavr.control.Try
import org.apache.commons.lang3.StringUtils
import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.Stream

abstract class BaseDataReader {

    companion object {
        @JvmField
        val PATTERN: Pattern = Pattern.compile(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")
        private const val HEADER: Long = 1
    }

    fun readLines(path: String): Stream<List<String>> {
        val resource = ClassPathResource(path)
        val lines = Try.withResources { BufferedReader(InputStreamReader(resource.inputStream)).lines() }
            .of { stream -> stream.collect(Collectors.toList()) }
            .getOrElseThrow { -> ReadDataException("Unable to read data in path: $path") }

        return lines.stream()
            .skip(HEADER)
            .map { line -> line.split(PATTERN) }
            .filter { line -> !line.stream().allMatch(StringUtils::isBlank)}
    }

    fun toBigDecimal(value: String): BigDecimal {
        val sanitizedValue = value.replace("\"", "")
            .replace(",", ".")

        return BigDecimal(sanitizedValue)
    }

    class ReadDataException(message: String) : RuntimeException(message)
}
