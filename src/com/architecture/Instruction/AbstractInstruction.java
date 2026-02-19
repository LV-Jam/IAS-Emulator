package com.architecture.Instruction;


import com.architecture.Nullable;

public abstract class AbstractInstruction {
    private final Nullable<Address> address;

    public AbstractInstruction() {
        this.address = new Nullable<>(null);
    }

    public Address getAddress() {
        return address.getValueOrElse(null);
    }

    public void setAddress(short address) {
        this.address.setValue(new Address(address));
    }

    public abstract void execute();

    @Override
    public String toString() {
        if (address.hasValue()) {
            return String.format(
                    "(%s, %d)",
                    this.getClass().getSimpleName(),
                    address.getValue().getValue()
            );
        } else {
            return String.format(
                    "(%s)",
                    this.getClass().getSimpleName()
            );
        }

    }
}
