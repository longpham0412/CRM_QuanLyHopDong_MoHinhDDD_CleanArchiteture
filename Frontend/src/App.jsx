import { useState, useEffect } from 'react';
import './App.css';

const API_CONTRACT = 'http://localhost:8081/api/hopdong';
const API_CUSTOMER = 'http://localhost:8081/api/khachhang';

function App() {
  const [hopDongs, setHopDongs] = useState([]);
  const [customers, setCustomers] = useState([]);
  
  // State cho Form Hợp đồng
  const [contractForm, setContractForm] = useState({ id: null, maHopDong: '', khachHangId: '', ngayKy: '', thoiHan: '' });
  const [isEditContract, setIsEditContract] = useState(false);

  // State cho Form Khách hàng
  const [customerForm, setCustomerForm] = useState({ maKhachHang: '', tenKhachHang: '', email: '', soDienThoai: '' });

  // 1. Tải dữ liệu từ Backend
  const fetchData = async () => {
    try {
      const [resContracts, resCustomers] = await Promise.all([fetch(API_CONTRACT), fetch(API_CUSTOMER)]);
      const contracts = await resContracts.json();
      const custs = await resCustomers.json();
      setHopDongs(contracts);
      setCustomers(custs);
    } catch (error) {
      console.error("Lỗi tải dữ liệu:", error);
    }
  };

  useEffect(() => { fetchData(); }, []);

  // 2. Xử lý Thêm Khách hàng
  const handleAddCustomer = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch(API_CUSTOMER, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(customerForm)
      });
      if (res.ok) {
        alert("Thêm khách hàng thành công!");
        setCustomerForm({ maKhachHang: '', tenKhachHang: '', email: '', soDienThoai: '' });
        fetchData(); // Cập nhật lại danh sách để chọn trong hợp đồng
      }
    } catch (error) { alert("Lỗi kết nối server!"); }
  };

  // 3. Xử lý Thêm/Sửa Hợp đồng
  const handleContractSubmit = async (e) => {
    e.preventDefault();
    const method = isEditContract ? 'PUT' : 'POST';
    const url = isEditContract ? `${API_CONTRACT}/${contractForm.id}` : API_CONTRACT;

    try {
      const res = await fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          ...contractForm,
          khachHangId: parseInt(contractForm.khachHangId),
          thoiHan: parseInt(contractForm.thoiHan)
        })
      });
      if (res.ok) {
        alert("Thao tác thành công!");
        resetContractForm();
        fetchData();
      }
    } catch (error) { alert("Lỗi!"); }
  };

  const resetContractForm = () => {
    setContractForm({ id: null, maHopDong: '', khachHangId: '', ngayKy: '', thoiHan: '' });
    setIsEditContract(false);
  };

  // Hàm tìm tên khách hàng từ ID
  const getCustomerName = (id) => {
    const customer = customers.find(c => c.id === id);
    return customer ? customer.tenKhachHang : `ID: ${id} (Chưa có tên)`;
  };

  return (
    <div className="container">
      <h1>Hệ Thống Quản Lý CRM</h1>

      <div className="grid-forms">
        {/* FORM KHÁCH HÀNG */}
        <section className="form-section">
          <h2>Thêm Khách Hàng</h2>
          <form onSubmit={handleAddCustomer}>
            <input placeholder="Mã KH" value={customerForm.maKhachHang} onChange={e => setCustomerForm({...customerForm, maKhachHang: e.target.value})} required />
            <input placeholder="Tên khách hàng" value={customerForm.tenKhachHang} onChange={e => setCustomerForm({...customerForm, tenKhachHang: e.target.value})} required />
            <input placeholder="Email" type="email" value={customerForm.email} onChange={e => setCustomerForm({...customerForm, email: e.target.value})} />
            <input placeholder="Số điện thoại" value={customerForm.soDienThoai} onChange={e => setCustomerForm({...customerForm, soDienThoai: e.target.value})} />
            <button type="submit" className="btn-add">Lưu Khách Hàng</button>
          </form>
        </section>

        {/* FORM HỢP ĐỒNG */}
        <section className="form-section">
          <h2>{isEditContract ? "Sửa Hợp Đồng" : "Thêm Hợp Đồng"}</h2>
          <form onSubmit={handleContractSubmit}>
            <input placeholder="Mã HĐ" value={contractForm.maHopDong} onChange={e => setContractForm({...contractForm, maHopDong: e.target.value})} required />
            
            {/* Chọn khách hàng từ danh sách thay vì nhập ID */}
            <select value={contractForm.khachHangId} onChange={e => setContractForm({...contractForm, khachHangId: e.target.value})} required>
              <option value="">-- Chọn khách hàng --</option>
              {customers.map(c => <option key={c.id} value={c.id}>{c.tenKhachHang} ({c.maKhachHang})</option>)}
            </select>

            <input type="date" value={contractForm.ngayKy} onChange={e => setContractForm({...contractForm, ngayKy: e.target.value})} required />
            <input placeholder="Thời hạn (tháng)" type="number" value={contractForm.thoiHan} onChange={e => setContractForm({...contractForm, thoiHan: e.target.value})} required />
            <button type="submit" className="btn-primary">{isEditContract ? "Cập nhật" : "Lưu Hợp Đồng"}</button>
          </form>
        </section>
      </div>

      {/* DANH SÁCH HỢP ĐỒNG */}
      <section className="list-section">
        <h2>Danh Sách Hợp Đồng Đã Nhập</h2>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Mã HĐ</th>
              <th>Khách Hàng</th> {/* Cột này sẽ hiện tên */}
              <th>Ngày Ký</th>
              <th>Thời Hạn</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {hopDongs.map(hd => (
              <tr key={hd.id}>
                <td>{hd.id}</td>
                <td>{hd.maHopDong}</td>
                <td className="highlight-name">{getCustomerName(hd.khachHangId)}</td>
                <td>{hd.ngayKy}</td>
                <td>{hd.thoiHan} tháng</td>
                <td>
                  <button onClick={() => { setContractForm(hd); setIsEditContract(true); }}>Sửa</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default App;