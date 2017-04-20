package com.pzj.core.stock.validator.stock;

import org.springframework.stereotype.Component;

/**
 * 释放库存参数验证器.
 * @author YRJ
 *
 */
@Component(value = "releaseStockValidator")
public class ReleaseStockValidator extends OccupyStockValidator {
}
