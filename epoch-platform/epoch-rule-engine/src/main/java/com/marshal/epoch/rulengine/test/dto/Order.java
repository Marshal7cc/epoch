package org.epoch.rulengine.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>name:Order</p>
 * <pre>
 *      description:
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/8/9
 */
@Data
@NoArgsConstructor
public class Order {
    private Double originalPrice;//订单原始价格，即优惠前价格
    private Double realPrice;//订单真实价格，即优惠后价格
}
