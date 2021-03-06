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
package edu.tsinghua.lumaqq.qq.packets.out.disk;

import java.nio.ByteBuffer;

import edu.tsinghua.lumaqq.qq.QQ;
import edu.tsinghua.lumaqq.qq.Util;
import edu.tsinghua.lumaqq.qq.annotation.DocumentalPacket;
import edu.tsinghua.lumaqq.qq.annotation.PacketName;
import edu.tsinghua.lumaqq.qq.annotation.RelatedPacket;
import edu.tsinghua.lumaqq.qq.beans.QQUser;
import edu.tsinghua.lumaqq.qq.packets.DiskOutPacket;
import edu.tsinghua.lumaqq.qq.packets.PacketParseException;
import edu.tsinghua.lumaqq.qq.packets.in.disk.DownloadReplyPacket;

/**
 * <pre>
 * 下载文件请求包
 * 1. 头部
 * 2. 网络硬盘拥有者，4字节
 * 3. 文件id，30字节
 * 4. 下载起始偏移，4字节
 * 5. 下载长度，4字节
 * </pre>
 *
 * @author luma
 */
@DocumentalPacket
@PacketName("下载文件请求包")
@RelatedPacket({DownloadReplyPacket.class})
public class DownloadPacket extends DiskOutPacket {
	private int diskOwner;
	private String fileId;
	private int offset;
	private int length;

	public DownloadPacket(ByteBuffer buf, int length, QQUser user) throws PacketParseException {
		super(buf, length, user);
	}

	public DownloadPacket(QQUser user) {
		super(QQ.QQ_DISK_CMD_DOWNLOAD, user);
	}
	
	@Override
	public String getPacketName() {
		return "Download Disk File Packet";
	}

	@Override
	protected void putBody(ByteBuffer buf) {
		buf.putInt(diskOwner);
		byte[] b = Util.getBytes(fileId);
		buf.put(b);
		buf.putInt(offset);
		buf.putInt(length);
	}

	/**
	 * @return the diskOwner
	 */
	public int getDiskOwner() {
		return diskOwner;
	}

	/**
	 * @param diskOwner the diskOwner to set
	 */
	public void setDiskOwner(int diskOwner) {
		this.diskOwner = diskOwner;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

}
