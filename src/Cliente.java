// https://medium.com/experiencecode/usando-records-em-java-9afecf7495b3

public record Cliente(String nome, String email) {
    @Override
    public String toString() {
        return String.format("%s (%s)", nome, email);
    }
}
