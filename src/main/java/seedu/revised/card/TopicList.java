package seedu.revised.card;

import java.util.List;

public class TopicList {
    private List<Topic> topics;

    public TopicList(List<Topic> topics) {
        this.topics = topics;
    }

    /**
     * Adds a <code>Topic</code> into a <code>topics</code>.
     *
     * @param topic The <code>Subject</code> to add into a <code>SubjectList</code>
     */
    public void add(Topic topic) {
        this.topics.add(topic);
    }

    /**
     * Gets the <code>Topic</code> at the index.
     *
     * @param index index number of the topic
     * @return <code>Topic</code> with the index number
     */
    public Topic get(int index) {
        return this.topics.get(index);
    }

    /**
     * Gets a <code>ArrayList</code> of Topics in a <code>TopicList</code> instance.
     *
     * @return the <code>ArrayList</code> of Topics
     */
    public List<Topic> getList() {
        return this.topics;
    }

    public boolean remove(Topic topic) {
        return true;
    }

    public boolean remove(int index) {
        try {
            topics.remove(index);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }
}
