package edu.ucb.pablostify.shared.register.domain.valueobject

@JvmInline
value class Email(val value: String) {
    init {
        require(value.contains("@") && value.contains(".")) { "Email inválido" }
    }
}
