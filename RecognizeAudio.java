// Design a online music identification service
// Create a fingerprint for music sample
// Sound is represented by sequences of frequency and magnitude
// A time domain signal

public class RecognizeAudio{
    public static void main(String[] args) {
        // Straight forward solution: at each frequency compares the magnitude of two audio data
        // If the difference stays within a small range then move to next frequency
        // Until reach the end of frequency then return true
        // Otherwise, if the set of frequency doesn't match or difference extends the range, return false
        // Limits: too many constraints may lead to false negative case
        //          takes too many space and time complexity if the length of frequency domain sequence is too long
        
        // Suppose audio is represented in frequency domain
        // Time complexity is O(n*l), n is the number of audios and l is the length of audio
        // Space complexity is O(n*l)
        

        // Another solution: divided the frequency domain into several subranges
        // For each subrange, record the maximum magnitude. All the magnitude constructs a fingerprint for the audio data
        // Compare fingerprints of two audio
        // Return true if all differences between two magnitude sequences at each point stands within a certain range
        // Otherwise, return false.
        
        // If there's n audio files with length l and m subranges, with m << l
        // Time complexity for preprocessing is O(n*l)
        // Time complexity for matching is O(n*m)
        // Space complexity is O(n*m)
    }
}