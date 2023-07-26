package api.bodys;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ArgBodyParams<T> {
    @Builder.Default
    public String jsonrpc = "2.0";
    @Builder.Default
    public int id = 1234567890;
    public String method;
    @SuppressWarnings("UnusedReturnValue")
    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    private T params;
}
