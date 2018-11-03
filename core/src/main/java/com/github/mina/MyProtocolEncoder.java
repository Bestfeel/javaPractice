package com.github.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Created by feel on 16/5/15.
 */
public class MyProtocolEncoder extends ProtocolEncoderAdapter {
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        ProtocalPack msg = (ProtocalPack) message;

        IoBuffer buf = IoBuffer.allocate(msg.getLength());
        buf.setAutoExpand(true);
        buf.putInt(msg.getLength());
        buf.put(msg.getFlag());
        if (msg.getContent() != null) {
            buf.put(msg.getContent().getBytes());
        }
        buf.flip();
        out.write(buf);


    }
}
