import "../../styles/Pagination.css";
function Pagination({
  totalEmployees,
  employesPerPage,
  setCurrentPage,
  currentPage,
}) {
  let pages = [];

  for (let i = 1; i <= Math.ceil(totalEmployees / employesPerPage); i++) {
    pages.push(i);
  }

  const goToFirstPage = () => {
    setCurrentPage(1);
  };

  const goToNextPage = () => {
    if (currentPage + 1 <= pages.length) setCurrentPage(currentPage + 1);
  };
  const goToLastPage = () => {
    setCurrentPage(pages.length);
  };

  const goToPrevPage = () => {
    if (currentPage - 1 > 0) setCurrentPage(currentPage - 1);
  };

  return (
    <>
      <div className="mt-5">
        <button
          onClick={goToPrevPage}
          className={`px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 mr-5 ml-5`}
        >
          Previous Page
        </button>

        <button
          onClick={goToNextPage}
          className={`px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600`}
        >
          Next Page
        </button>
      </div>
      <span className="flex space-x-2 pagination mt-5 ml-5">
        <button
          onClick={goToFirstPage}
          className={`px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 ${
            currentPage === 1 ? "active" : ""
          }`}
        >
          First
        </button>

        {pages.map((page, index) => {
          if (page === 1 || page === pages.length) {
            return null; // Skip rendering page number for first and last page
          }
          return (
            <button
              onClick={() => {
                setCurrentPage(page);
              }}
              key={index}
              className={`px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 ${
                page === currentPage ? "active" : ""
              }`}
            >
              {page}
            </button>
          );
        })}

        <button
          onClick={goToLastPage}
          className={`px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 ${
            currentPage === pages.length ? "active" : ""
          }`}
        >
          Last
        </button>
      </span>
    </>
  );
}

export default Pagination;
