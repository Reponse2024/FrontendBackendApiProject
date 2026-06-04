package backend.constants.productsConstants;

import java.util.List;
import java.util.Map;

public class ProductData {
    public static final String NAME = "Smartphone X";
    public static final String DESCRIPTION = "High-end smartphone with OLED display";
    public static final double PRICE = 999.99;
    public static final double COMPARE_PRICE = 1099.99;
    public static final String CATEGORY_ID = "electronics";
    public static final List<String> TAGS = List.of("smartphone", "electronics", "mobile");
    public static final boolean IS_FEATURED = true;
    public static final boolean IS_FLASH_SALE = true;
    public static final double FLASH_SALE_PRICE = 899.99;
    public static final String SLUG = "Distressed Slim Fit Jeans";
    public static final String ID = "12345";

    public static final List<Map<String, Object>> VARIANTS = List.of(
            Map.of("size", "128GB", "color", "Black", "colorHex", "#000000", "sku", "SPX128B", "stock", 50, "price", 999.99)
    );
        public static final String VALID_SLUG_SMARTPHONE = "smartphone-x";
        public static final String INVALID_SLUG = "nonexistent-slug";

        public static final String VALID_ID_SMARTPHONE = "12345";
        public static final String INVALID_ID = "99999";
    }

