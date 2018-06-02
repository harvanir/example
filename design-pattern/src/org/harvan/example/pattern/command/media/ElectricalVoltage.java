package org.harvan.example.pattern.command.media;

public class ElectricalVoltage implements ElectroMagneticSignal {
    private String data;

    @Override
    public void setData(String data) {
	this.data = data;
    }

    public String getData() {
	return data;
    }

    @Override
    public String toString() {
	return "ElectricalVoltage [data=" + data + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((data == null) ? 0 : data.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ElectricalVoltage other = (ElectricalVoltage) obj;
	if (data == null) {
	    if (other.data != null)
		return false;
	} else if (!data.equals(other.data))
	    return false;
	return true;
    }
}