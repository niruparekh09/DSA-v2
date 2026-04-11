import java.util.*;

public class DesignTwitter {
    // userId -> list of (tweetId, timestamp)
    Map<Integer, List<int[]>> tweets;

    // followerId -> set of followees
    Map<Integer, Set<Integer>> followees;

    int time;

    public DesignTwitter() {
        tweets = new HashMap<>();
        followees = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());

        tweets.get(userId).add(new int[]{tweetId, time++});
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]); // For comparing timing of the posts

        Set<Integer> users = new HashSet<>();
        users.add(userId); // added the user himself

        // added all his followees
        if (followees.containsKey(userId)) {
            users.addAll(followees.get(userId));
        }

        for (int u : users) {
            if (!tweets.containsKey(u)) continue;
            for (int[] t : tweets.get(u)) {
                pq.offer(t);
            }
        }

        List<Integer> res = new ArrayList<>();
        int count = 0;

        while (!pq.isEmpty() && count < 10) {
            res.add(pq.poll()[0]);
            count++;
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        followees.putIfAbsent(followerId, new HashSet<>());

        followees.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followees.containsKey(followerId)) {
            followees.get(followerId).remove(followeeId);
        }
    }
}

class TwitterTester {
    public static void main(String[] args) {
        DesignTwitter twitter = new DesignTwitter();

        // ["Twitter"]
        // Constructor already called

        // ["postTweet", [1, 5]]
        twitter.postTweet(1, 5);

        // ["getNewsFeed", [1]]
        System.out.println(twitter.getNewsFeed(1));

        // ["follow", [1, 2]]
        twitter.follow(1, 2);

        // ["postTweet", [2, 6]]
        twitter.postTweet(2, 6);

        // ["getNewsFeed", [1]]
        System.out.println(twitter.getNewsFeed(1));

        // ["unfollow", [1, 2]]
        twitter.unfollow(1, 2);

        // ["getNewsFeed", [1]]
        System.out.println(twitter.getNewsFeed(1));
    }
}
