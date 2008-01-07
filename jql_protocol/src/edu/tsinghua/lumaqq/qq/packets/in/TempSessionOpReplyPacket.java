/*
* LumaQQ - Java QQ Client
*
* Copyright (C) 2004 luma <stubma@163.com>
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
package edu.tsinghua.lumaqq.qq.packets.in;

import static edu.tsinghua.lumaqq.qq.events.QQEvent.*;
import java.nio.ByteBuffer;

import edu.tsinghua.lumaqq.qq.QQ;
import edu.tsinghua.lumaqq.qq.Util;
import edu.tsinghua.lumaqq.qq.annotation.DocumentalPacket;
import edu.tsinghua.lumaqq.qq.annotation.LinkedEvent;
import edu.tsinghua.lumaqq.qq.annotation.PacketName;
import edu.tsinghua.lumaqq.qq.annotation.RelatedPacket;
import edu.tsinghua.lumaqq.qq.beans.QQUser;
import edu.tsinghua.lumaqq.qq.packets.BasicInPacket;
import edu.tsinghua.lumaqq.qq.packets.PacketParseException;
import edu.tsinghua.lumaqq.qq.packets.out.TempSessionOpPacket;

/**
 * <pre>
 * 临时会话操作回复包，格式为
 * 1. 头部
 * 2. 子命令，1字节
 * 当2部分为0x01时，格式为
 * 3. 接收者QQ号，4字节
 * 4. 回复码，1字节
 * 5. 回复消息长度，1字节
 * 6. 回复消息
 * 7. 尾部
 * </pre>
 * 
 * @author luma
 */
@DocumentalPacket
@PacketName("临时会话操作回复包")
@RelatedPacket({TempSessionOpPacket.class})
@LinkedEvent({QQ_SEND_TEMP_SESSOIN_IM_SUCCESS, QQ_SEND_TEMP_SESSION_IM_FAIL})
public class TempSessionOpReplyPacket extends BasicInPacket {
	public String replyMessage;
	public int receiver;
	public byte replyCode;
	public byte subCommand;

	public TempSessionOpReplyPacket(ByteBuffer buf, int length, QQUser user) throws PacketParseException {
		super(buf, length, user);
	}

	@Override
	public String getPacketName() {
		switch(subCommand) {
			case QQ.QQ_SUB_CMD_SEND_TEMP_SESSION_IM:
				return "Temp Session IM Reply Packet";
			default:
				return "Unknown Temp Session Op Reply Packet";
		}
	}
	
	@Override
	protected void parseBody(ByteBuffer buf) throws PacketParseException {
		subCommand = buf.get();
		switch(subCommand) {
			case QQ.QQ_SUB_CMD_SEND_TEMP_SESSION_IM:
				receiver = buf.getInt();
				replyCode = buf.get();
				int len = buf.get() & 0xFF;
				replyMessage = Util.getString(buf, len);
				break;
		}
	}
}