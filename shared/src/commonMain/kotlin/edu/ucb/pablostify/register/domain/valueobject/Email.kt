package edu.ucb.pablostify.register.domain.valueobject

@JvmInline
value class Email(val value: String) {
    init {
        require(value.contains("@") && value.contains(".")) { "Email inválido" }
    }
}
