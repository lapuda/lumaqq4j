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
package edu.tsinghua.lumaqq.tools;

import java.util.ArrayList;
import java.util.List;


/**
 * 封装生成packet.html需要的数据
 *
 * @author luma
 */
public class PacketBean {
	public String html;
	public String name;
	public String qName;
	public String format;
	public List<PacketBean> relatedPackets;
	public List<EventBean> events;
	
	public PacketBean() {
		html = name = qName = format = "";
		relatedPackets = new ArrayList<PacketBean>();
		events = new ArrayList<EventBean>();
	}
}
