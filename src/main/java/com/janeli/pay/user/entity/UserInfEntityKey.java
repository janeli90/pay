package com.janeli.pay.user.entity;

public class UserInfEntityKey {
    /**
     * 用户手机号
     */
    private String usrTel;

    /**
     * oem编号
     */
    private String channelId;

    public String getUsrTel() {
        return usrTel;
    }

    public void setUsrTel(String usrTel) {
        this.usrTel = usrTel == null ? null : usrTel.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", usrTel=").append(usrTel);
        sb.append(", channelId=").append(channelId);
        sb.append("]");
        return sb.toString();
    }
}