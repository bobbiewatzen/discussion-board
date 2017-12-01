package com.jsun.site;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.jsun.site.entity.Reply;

@Repository
public class InMemoryReplyRepository implements ReplyRepository {
	private final Map<Long, Reply> database = new Hashtable<>();
	private volatile long replyIdSequence = 1L;

	@Override
	public List<Reply> getForDiscussion(long id) {
		ArrayList<Reply> list = new ArrayList<>(this.database.values());
		list.removeIf(r -> r.getDiscussionId() != id);
		return list;
	}

	@Override
	public void add(Reply reply) {
		reply.setId(this.getNextReplyId());
		this.database.put(reply.getId(), reply);
	}

	@Override
	public void update(Reply reply) {
		this.database.put(reply.getId(), reply);
	}

	@Override
	public void deleteForDiscussion(long id) {
		this.database.entrySet().removeIf(e -> e.getValue().getDiscussionId() == id);
	}

	private synchronized long getNextReplyId() {
		return this.replyIdSequence++;
	}
}
