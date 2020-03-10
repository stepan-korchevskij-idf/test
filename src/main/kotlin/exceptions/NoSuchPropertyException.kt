package exceptions

class NoSuchPropertyException(nameProperty: String) : RuntimeException("Property '$nameProperty' must be initialized.")