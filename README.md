# Based on Colour and Shape Product Recommendation System

## Colour Features:
Firstly, load the input image and convert it to a BufferedImage object. Compute the histogram of the chosen color of the input image using the BufferedImage object. For each image in the database, compute its histogram of the same color. Compute the distance between the histograms of the input image and each database image using a distance metric e.g., Euclidean distance. If the distance is below a certain threshold, mark the corresponding database image as selected. Display the selected images.

## Shape Features:
Use the Fourier coefficients to separate the image by shape feature. This can be done by setting the frequencies outside a certain range to zero and then computing the inverse Fourier transform. The range of frequencies to keep can be determined based on the values of the Fourier coefficients. For example, to keep only the low-frequency components, you can set the frequencies corresponding to c[1], c[2], c[3], and c[4] to zero and then compute the IDFT. After computing the Fourier coefficients, we can use them to separate the image into different components based on their shape features. One common technique is to threshold the magnitude of the Fourier coefficients to eliminate the high-frequency noise and keep only the low-frequency information. We can then reconstruct the image using only the low-frequency information.

# https://github.com/Prital-Patel/Shape-and-Color-Based-Product-Recommendation-System-/blob/master/Output.jpg
