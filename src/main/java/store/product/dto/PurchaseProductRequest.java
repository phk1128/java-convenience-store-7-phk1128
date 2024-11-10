package store.product.dto;

import java.util.Objects;

public record PurchaseProductRequest(
        String name,
        int quantity
) {

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseProductRequest)) {
            return false;
        }
        final PurchaseProductRequest request = (PurchaseProductRequest) o;
        return Objects.equals(name, request.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
