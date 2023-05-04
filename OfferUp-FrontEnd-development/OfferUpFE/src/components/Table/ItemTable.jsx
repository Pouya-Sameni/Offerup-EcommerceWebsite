import React, { useCallback, useMemo, useState } from "react";
import MaterialReactTable from "material-react-table";
import { Box, IconButton, Tooltip } from "@mui/material";
import { Preview } from "@mui/icons-material";
import { useEffect } from "react";
// import { getAllUsers } from "../../services/Users_Query";
import { useNavigate } from "react-router-dom";
import { getProductDetails } from "../../services/Catalogue";

const ItemTable = (auctionData) => {
  const [itemInfo, setItemInfo] = useState([]);

  useEffect(() => {
    const fetchdata = async () => {
      console.log(auctionData);
      setTableData([]);
      const data = auctionData.auctionData;
      for (let i = 0; i < data.length; i++) {
        // Update the value of the ItemName field
        const itemNameTemp = await getProductDetails(data[i].itemId);

        data[i].ItemName = itemNameTemp.itemName;
        if (typeof data[i].currentPrice === "number") {
          data[i].currentPrice = data[i].currentPrice;
        }

        if (data[i].auctionEnded) {
          data.splice(i, 1);
        }
      }

      setTableData(data);
    };

    // Call the function
    fetchdata();
  }, [auctionData]);

  const navigate = useNavigate();

  const [createModalOpen, setCreateModalOpen] = useState(false);
  const [tableData, setTableData] = useState(() => []);
  const [validationErrors, setValidationErrors] = useState({});

  const toAuctionPage = async (givenRow) => {
    if (givenRow.auctionType === "FORWARD") {
      const itemNameTemp = await getProductDetails(givenRow.itemId);

      navigate("/forwardAuction", {
        state: {
          auctionInfo: givenRow,
          itemInfo: itemNameTemp,
        },
      });
    } else {
      const itemNameTemp = await getProductDetails(givenRow.itemId);
      navigate("/dutchAuction", {
        state: {
          auctionInfo: givenRow,
          itemInfo: itemNameTemp,
        },
      });
    }
  };

  const tableContainerStyles = {
    marginTop: "100px",
    height: "500px",
    overflow: "auto",
  };

  const columns = useMemo(() => [
    {
      accessorKey: "auctionId",
      header: "Auction ID",
      enableColumnOrdering: false,

      size: 140,
    },
    {
      accessorKey: "ItemName",
      header: "Item",
      enableColumnOrdering: false,

      size: 140,
    },
    {
      accessorKey: "auctionType",
      header: "Auction Type",
      enableColumnOrdering: false,
    },
    {
      accessorKey: "currentPrice",
      header: "Price",
      size: 80,
      enableColumnOrdering: false,
    },
    {
      accessorKey: "endTimeOfAuction",
      header: "Expires",
      size: 140,
      enableColumnOrdering: false,
    },
    
  ]);

  return (
    <>
      <div>
        {/* <h1 className="flex-1 font-poppins font-semibold text-[54px] text-babyblue ">
          Pick the Auction of Your Choice...
        </h1> */}
      </div>
      <div style={tableContainerStyles}>
        <MaterialReactTable
          displayColumnDefOptions={{
            "mrt-row-actions": {
              muiTableHeadCellProps: {
                align: "center",
                children: "Preview",
              },
              size: 120,
            },
          }}
          columns={columns}
          data={tableData}
          editingMode="modal" //default
          enableColumnOrdering
          enableEditing
          renderRowActions={({ row, table }) => (
            <div style={{ display: "flex", justifyContent: "center" }}>
              <Tooltip arrow placement="right" title="Preview">
                <IconButton
                  color="primary"
                  onClick={() => {
                    toAuctionPage(row.original);
                  }}
                >
                  <Preview />
                </IconButton>
              </Tooltip>
            </div>
          )}
        />
      </div>
    </>
  );
};

// //example of creating a mui dialog modal for creating new rows
// export const CreateNewAccountModal = ({ open, columns, onClose, onSubmit }) => {
//   const [values, setValues] = useState(() =>
//     columns.reduce((acc, column) => {
//       acc[column.accessorKey ?? ""] = "";
//       return acc;
//     }, {})
//   );

//   const handleSubmit = () => {
//     //put your validation logic here
//     onSubmit(values);
//     onClose();
//   };
// };

// const validateRequired = (value) => !!value.length;
// const validateEmail = (email) =>
//   !!email.length &&
//   email
//     .toLowerCase()
//     .match(
//       /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
//     );
// const validateAge = (age) => age >= 18 && age <= 50;

export default ItemTable;
