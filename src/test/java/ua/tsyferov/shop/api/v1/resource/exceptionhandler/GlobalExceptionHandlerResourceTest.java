package ua.tsyferov.shop.api.v1.resource.exceptionhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ua.tsyferov.shop.api.v1.resource.ShopResource;
import ua.tsyferov.shop.config.ResourceTestConfig;
import ua.tsyferov.shop.exception.ResourceNotFoundException;
import ua.tsyferov.shop.model.ApiErrorDto;
import ua.tsyferov.shop.service.DateTimeService;
import ua.tsyferov.shop.service.ProductService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.tsyferov.shop.api.v1.resource.ShopResource.BASE_URL;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ShopResource.class)
@Import(ResourceTestConfig.class)
@DisplayName("GlobalExceptionHandlerResource")
class GlobalExceptionHandlerResourceTest {

    private static final LocalDateTime CURRENT_DATE_TIME = LocalDateTime.of(2022, 12, 16, 10, 30);

    private static final String ERROR_MESSAGE = "Error message";

    private static final String REGEXP = "%5EE.%2A%24";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private DateTimeService dateTimeServiceMock;

    @Autowired
    private ProductService productServiceMock;

    @BeforeEach
    void setUp() {
        when(dateTimeServiceMock.now()).thenReturn(CURRENT_DATE_TIME);
    }

    @Test
    void shouldHandleResourceNotFoundExceptionAndReturnApiError() throws Exception {
        ApiErrorDto expectedError = new ApiErrorDto.ApiErrorDtoBuilder()
            .withTimestamp(CURRENT_DATE_TIME)
            .withErrorMessage(ERROR_MESSAGE)
            .withPath("")
            .build();

        when(productServiceMock.getProductsByNameNotMatchRegexp(REGEXP))
            .thenThrow(new ResourceNotFoundException(ERROR_MESSAGE));

        String responseBodyJson = mockMvc.perform(get(BASE_URL + "/product?nameFilter=" + REGEXP))
            .andExpect(status().isNotFound())
            .andReturn()
            .getResponse()
            .getContentAsString();

        ApiErrorDto result = mapper.readValue(responseBodyJson, ApiErrorDto.class);

        assertThat(result).usingRecursiveComparison().ignoringFields("path", "timestamp").isEqualTo(expectedError);
        assertThat(result.getTimestamp()).isCloseTo(expectedError.getTimestamp(), within(1, ChronoUnit.SECONDS));
    }

    @Test
    void shouldHandleIllegalArgumentExceptionAndReturnApiError() throws Exception {
        ApiErrorDto expectedError = new ApiErrorDto.ApiErrorDtoBuilder()
            .withTimestamp(CURRENT_DATE_TIME)
            .withErrorMessage(ERROR_MESSAGE)
            .withPath("")
            .build();

        when(productServiceMock.getProductsByNameNotMatchRegexp(REGEXP))
            .thenThrow(new IllegalArgumentException(ERROR_MESSAGE));

        String responseBodyJson = mockMvc.perform(get(BASE_URL + "/product?nameFilter=" + REGEXP))
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse()
            .getContentAsString();

        ApiErrorDto result = mapper.readValue(responseBodyJson, ApiErrorDto.class);

        assertThat(result).usingRecursiveComparison().ignoringFields("path", "timestamp").isEqualTo(expectedError);
        assertThat(result.getTimestamp()).isCloseTo(expectedError.getTimestamp(), within(1, ChronoUnit.SECONDS));
    }
}