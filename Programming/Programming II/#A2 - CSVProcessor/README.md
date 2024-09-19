### CSV Reader and Processor

Enhancement of work A1 where, in addition to reading, it performs data processing, resembling the implementation of functionalities with the Python Pandas library.

#### Printing Particularities
The indices have a fixed print size of 10 to avoid deformation in alternative printing formats.

#### Peculiarities of Option 6 (Selection)
The variables need to be informed in the order they appear in the file. The printing logic (and column size calculation) is based on this strict order; therefore, if not followed, the printing will not have the correct spacing.

#### Peculiarities of Option 7 (Missing Data)
In the implementation of this option, the altered data is stored in an auxiliary struct, used for visual analysis through Option 1, but is only definitively replaced if the option to do so is selected by choosing "5) Return to the main menu."

When there are no more NaN records, the function only prints the header and the indication of rows x columns.