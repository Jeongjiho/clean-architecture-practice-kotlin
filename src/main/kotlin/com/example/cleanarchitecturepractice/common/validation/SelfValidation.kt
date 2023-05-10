package com.example.cleanarchitecturepractice.common.validation

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-22
 */
open class SelfValidation {
    var validator: Validator = Validation.buildDefaultValidatorFactory().validator

    protected fun validationSelf() {
        var violation = this.validator.validate(this)
        if(violation.isNotEmpty()) {
            throw ConstraintViolationException(violation)
        }
    }
}