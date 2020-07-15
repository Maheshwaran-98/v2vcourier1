package com.v2v.dao;

public interface parcelcheckdao {
    public abstract boolean  loginValidation(String username,String password);
    public abstract int createProfile(Parceltrackcheckmodel parceltrackcheckmodel);
    public abstract Object createNewParcel(ParcelRegistermodel parcelRegistermodel);
    public abstract List<ParcelRegistermodel> getProfiles();
    
	public abstract ParcelTrackDetails getTrackIdDetails(String track_id);

}
