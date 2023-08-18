package org.acme.travels;

public class VisaResolution {

    private boolean approved;
    private String reason;

    public VisaResolution() {

    }

    public VisaResolution(boolean approved, String reason) {
        this.approved = approved;
        this.reason = reason;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "VisaResolution [approved=" + approved + ",reason=" + reason + "]";
    }
}
