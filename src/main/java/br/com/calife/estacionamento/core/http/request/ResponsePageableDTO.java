package br.com.calife.estacionamento.core.http.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponsePageableDTO<T> {

    protected static final long DEFAULT_RECORDS = NumberUtils.LONG_ZERO;

    private Long records;
    private List<T> items;
    private Integer pages;
    private Integer page;
    @JsonProperty("record_from")
    private Integer recordFrom;
    @JsonProperty("record_to")
    private Integer recordTo;

    public ResponsePageableDTO(int records, List<T> items, RequestPageableDTO pageable) {
        this((long) records, items, pageable);
    }

    public ResponsePageableDTO(long records, List<T> items, RequestPageableDTO pageable) {
        this.records = records;
        this.items = items;
        this.pages = (int) Math.ceil((double) this.records / pageable.getRpp());
        this.page = pageable.getPage();
        if (ObjectUtils.isEmpty(this.items)) {
            this.recordFrom = 1;
            this.recordTo = Math.toIntExact(this.records);
        } else {
            this.recordFrom = (this.page * pageable.getRpp()) - pageable.getRpp() + 1;
            this.recordTo = (int) (this.page.equals(this.pages) ? this.records : this.page * pageable.getRpp());
        }
    }
}