package io.github.gxrj.janitory.domain.builders;

import android.graphics.Bitmap;
import io.github.gxrj.janitory.domain.models.Address;
import io.github.gxrj.janitory.domain.models.Call;
import io.github.gxrj.janitory.domain.models.Citizen;

public class CallBuilder {
    private final Call call;

    public CallBuilder() {
        call = new Call();
    }

    public CallBuilder status( String status ) {
        call.setStatus( status );
        return this;
    }

    public CallBuilder author( Citizen author ) {
        call.setAuthor( author );
        return this;
    }

    public CallBuilder address( Address address ) {
        call.setAddress( address );
        return this;
    }

    public CallBuilder duty( String duty ) {
        call.setDuty( duty );
        return this;
    }

    public CallBuilder description( String description ) {
        call.setDescription( description );
        return this;
    }

    public CallBuilder protocol( String protocol ) {
        call.setProtocol( protocol );
        return this;
    }

    public CallBuilder image( Bitmap image ) {
        call.setImage( image );
        return this;
    }

    public Call build() {
        return call;
    }
}
